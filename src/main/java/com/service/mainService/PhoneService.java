package com.service.mainService;

import com.annotations.MyAutowired;
import com.annotations.MySingleton;
import com.model.product.Manufacturer;
import com.model.phone.OperationSystem;
import com.model.phone.Phone;
import com.parser.JsonCompiler;
import com.parser.XmlParser;
import com.repository.hibernateRepository.PhoneRepositoryHibernate;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.function.Function;

@MySingleton
public class PhoneService extends ProductService<Phone> {
    private final PhoneRepositoryHibernate repository;
    private static PhoneService instance;
@MyAutowired
    private PhoneService(final PhoneRepositoryHibernate repository) {
        super(repository);
        this.repository = repository;
    }


    public static PhoneService getInstance() {
        if (instance == null) {
            instance = new PhoneService(PhoneRepositoryHibernate.getInstance());
        }
        return instance;
    }

    public static PhoneService getInstance(final PhoneRepositoryHibernate repository) {
        if (instance == null) {
            instance = new PhoneService(repository);
        }
        return instance;
    }
    @Override
    protected Phone creatProduct() {
        return new Phone(
                Phone.class.getSimpleName() + "-" + RANDOM.nextInt(1000),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000.0),
                "Model-" + RANDOM.nextInt(10),
                getRandomManufacturer()
        );
    }

    private Phone productFromMap(Map<String,Object> map){
        Function<Map<String,Object>,Phone> function = (m) -> {
            Phone phone = new Phone((String) m.get("title"),
                    Integer.parseInt((String) m.get("count")),
                    Integer.parseInt((String) m.get("price")),
                    (String) m.get("model"),
                    Manufacturer.APPLE,
                    new OperationSystem((String) m.get("designation"), Integer.parseInt((String) m.get("version"))),
                    LocalDateTime.parse((CharSequence) m.get("created"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))
                    );
            return phone;
        };
        return function.apply(map);
    }

    public Phone xmlPhoneFromMap(InputStream inputStream){
        XmlParser xmlParser = new XmlParser();
        Phone p = productFromMap(xmlParser.phoneFromFileToMap(inputStream));
        return p;
    }

    public Phone jsonPhoneFromMap(InputStream inputStream){
        JsonCompiler jsonCompiler = new JsonCompiler();
        Phone p = productFromMap(jsonCompiler.phoneFromFileToMap(inputStream));
        return p;
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }

}
