package com.service.mainService;

import com.model.*;
import com.model.comparator.ProductComporator;
import com.model.comparator.SortByCount;
import com.model.comparator.SortByName;
import com.model.comparator.SortByPrice;
import com.repository.crudRepository.CrudRepository;


import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public abstract class ProductService <T extends Product>{

    protected static final Random RANDOM = new Random();
    protected final CrudRepository<T> repository;

    protected ProductService(CrudRepository<T> repository) {
        this.repository = repository;
    }

    public void createAndSave(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("count must been bigger then 0");
        }
        List<T> products = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            final T phone = creatProduct();
            products.add(phone);
        }
        repository.saveAll(products);
    }

    protected abstract T creatProduct();

    public void save(T phone) {
        if (phone.getCount() == 0) {
            phone.setCount(-1);
        }
        repository.save(phone);
    }

    public List<T> getAll() {
        return repository.getAll();
    }

    public void printAll() {
        for (T phone : repository.getAll()) {
            System.out.println(phone);
        }
    }

    public void delete(T product){
       repository.delete(product.getId());
    }

    public void update(T product){
        repository.update(product);
    }


    public void sortProduct(){
        Collections.sort(repository.getAll(),new ProductComporator());
    }

    public void sortPrice(){
        Collections.sort(repository.getAll(),new SortByPrice());
    }

    public  void sortName(){
        Collections.sort(repository.getAll(),new SortByName());
    }
    public void sortCount(){
        Collections.sort(repository.getAll(),new SortByCount());
    }

    public  Predicate<List<T>> isValid =
            (products) -> products.stream().allMatch(product -> product.getPrice() != 0);
    public List<T> filterMoreThan(Double price) {
          return repository.getAll().stream()
                .filter((n)-> price < n.getPrice())
                .collect(Collectors.toList());
    }

    public int sumOfPrise() {
        return   repository.getAll().stream()
                .map((n) -> n.getCount())
                .reduce(0,Integer::sum);
    }
    public Map<String, String> sortList(){
        return repository.getAll().stream()
                .sorted(new SortByName())
                .distinct()
                .collect(Collectors.toMap(Product::getId,Product::getTitle,(o1,o2) -> o2));
    }

    public DoubleSummaryStatistics summaryStatistics() {
        return repository.getAll().stream().mapToDouble(Product::getPrice).summaryStatistics();
    }

    public List<T> isPrice(){
      List<T> result = filterBy(repository, product -> product.getPrice() == 0 );
      return result;
    }

    private  List<T> filterBy(CrudRepository<T> products, MyPredicate predicate) {
        List<T> result = new ArrayList<>();
        for (T i: products.getAll()) {
            if(predicate.test(i)) {
                result.add(i);
            }
        }

        return result;
    }

    //public abstract T productFromMap(Map<String,Object> map);

}
