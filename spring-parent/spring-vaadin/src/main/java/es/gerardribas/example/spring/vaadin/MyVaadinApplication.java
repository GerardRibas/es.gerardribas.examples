package es.gerardribas.example.spring.vaadin;

import com.vaadin.Application;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;
import es.gerardribas.example.common.dao.ProductDao;
import es.gerardribas.example.common.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@SuppressWarnings("serial")
@Configurable(preConstruction = true)
public class MyVaadinApplication extends Application {

    private Window window;

    @Autowired
    private ProductDao productDao;

    private Table productTable;

    private Container productContainer;

    @Override
    public void init() {
        window = new Window("My Vaadin Application");
        setMainWindow(window);

        Button button = new Button("Show Products");
        button.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                fillTable();
                productTable.setVisible(true);
            }
        });
        window.addComponent(new Label("Simple PoC for demostrate Spring - Vaadin configurable."));
        window.addComponent(button);
        window.addComponent(addTableToWindow());
    }

    private void fillTable() {
        if (productContainer == null) {
            productContainer = new BeanItemContainer<Product>(Product.class, productDao.findAll(Product.class));
            productTable.setContainerDataSource(productContainer);
        }

    }

    private Table addTableToWindow() {
        productTable = new Table();
        productTable.setVisible(false);
        return productTable;
    }


}
