package com.discoversdk.ui;

import com.discoversdk.model.Person;
import com.discoversdk.service.PersonService;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@SpringUI
@Title("Hello Vaadin")
public class HelloWorld extends UI {

    Logger logger = LoggerFactory.getLogger("HelloWorld");

    @Autowired
    private PersonService personService;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        VerticalLayout mainLayout = new VerticalLayout();
        setContent(mainLayout);

        //mainLayout.setSizeFull();

        FormLayout formLayout = new FormLayout();

        TextField tfName = new TextField("Name");
        tfName.setRequiredIndicatorVisible(true);
        tfName.setIcon(VaadinIcons.USER);
        tfName.setMaxLength(20);

        final TextField tfAge = new TextField("Age");
        tfAge.setRequiredIndicatorVisible(true);
        tfAge.setIcon(VaadinIcons.BELL);
        tfAge.setMaxLength(2);

        DateField tfDateOfBirth = new DateField();
        tfDateOfBirth.setDateFormat("yyyy-MM-dd");
        tfDateOfBirth.setPlaceholder("yyyy-mm-dd");
        tfDateOfBirth.setRequiredIndicatorVisible(true);
        tfDateOfBirth.setIcon(VaadinIcons.DATE_INPUT);
        tfDateOfBirth.setValue(LocalDate.now());

        Button btnSubmit = new Button("Save");

        formLayout.addComponent(tfName);
        formLayout.addComponent(tfAge);
        formLayout.addComponent(tfDateOfBirth);
        formLayout.addComponent(btnSubmit);

        formLayout.setWidth(null);
        mainLayout.addComponent(formLayout);
        //mainLayout.setComponentAlignment(formLayout, Alignment.MIDDLE_CENTER);

        Grid<Person> grid = new Grid<>();
        grid.addColumn(Person::getName).setCaption("Name");
        grid.addColumn(Person::getAge).setCaption("Age");
        grid.addColumn(Person::getDateOfBirth).setCaption("DOB");

        btnSubmit.addClickListener(click -> {

            if(isValidInt(tfAge)) {
                Person savedPerson = personService.savePerson(
                        new Person(tfName.getValue(),
                                Integer.valueOf(tfAge.getValue()), tfDateOfBirth.getValue()));

                Notification.show("Person saved with ID : " + savedPerson.getId());
                logger.info("Person saved with ID : {}", savedPerson.getId());
                grid.setItems(personService.getAllPerson());
            } else {
                Notification.show("Invalid data.");
            }
        });

        mainLayout.addComponent(grid);
    }

    private boolean isValidInt(TextField tfAge) {

        boolean isValid = true;

        try {
            String ageValue = tfAge.getValue();
            int age = Integer.valueOf(ageValue);
        } catch (Exception ex) {
            logger.error("Invalid Integer.");
            isValid = false;
        }

        return isValid;
    }

}
