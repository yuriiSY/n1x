package com.service.mainService;

import com.annotations.MyAutowired;
import com.annotations.MySingleton;
import com.model.product.Manufacturer;
import com.model.tablet.Tablet;
import com.repository.dbRepository.TabletRepositoryDB;

import java.util.Map;
import java.util.function.Function;
@MySingleton
public class TabletService extends ProductService<Tablet> {
    private final TabletRepositoryDB repository;
    private static TabletService instance;
@MyAutowired
    private TabletService(final TabletRepositoryDB repository) {
        super(repository);
        this.repository = repository;
    }


    public static TabletService getInstance() {
        if (instance == null) {
            instance = new TabletService(TabletRepositoryDB.getInstance());
        }
        return instance;
    }

    public static TabletService getInstance(final TabletRepositoryDB repository) {
        if (instance == null) {
            instance = new TabletService(repository);
        }
        return instance;
    }

    @Override
    protected Tablet creatProduct() {
        return new Tablet(
                Tablet.class.getSimpleName() + "-" + RANDOM.nextInt(1000),
                RANDOM.nextInt(500),
                RANDOM.nextDouble(1000.0),
                "Model-" + RANDOM.nextInt(10),
                getRandomManufacturer()
        );
    }


    private Tablet productFromMap(Map<String, Object> map) {
        Function<Map<String,Object>,Tablet> function = (m) -> {
            Tablet tablet  = new Tablet((String) m.get("title"),
                    (Integer) m.get("count"),
                    (Integer) m.get("price"),
                    (String) m.get("model"),
                    Manufacturer.APPLE);
            return tablet;
        };
        return function.apply(map);
    }

    private Manufacturer getRandomManufacturer() {
        final Manufacturer[] values = Manufacturer.values();
        final int index = RANDOM.nextInt(values.length);
        return values[index];
    }
}
