package com.uniyaz.sorun.ui.views;

import com.uniyaz.sorun.dao.CategoryDao;
import com.uniyaz.sorun.dao.IssueDao;
import com.uniyaz.sorun.domain.Category;
import com.uniyaz.sorun.domain.EnumIssueState;
import com.uniyaz.sorun.domain.Issue;
import com.uniyaz.sorun.ui.components.SaveButton;
import com.uniyaz.sorun.ui.components.StTextField;
import com.vaadin.ui.*;

import java.util.Date;

public class AddIssueView extends VerticalLayout {

    private StTextField idField;
    private FormLayout mainLayout;
    private TextArea addressArea;
    private TextField topicField;
    private TextArea contentArea;
    private DateField dateField;
    private ComboBox categoryComboBox;
    private ComboBox enumComboBox;

    public AddIssueView() {
        buildMainLayout();
    }

    public void fillViewByIssue(Issue issue) {
        idField.setValue(issue.getId().toString());
        addressArea.setValue(issue.getAddress());
        topicField.setValue(issue.getTopic());
        contentArea.setValue(issue.getContent());
        dateField.setValue(issue.getDate());
        categoryComboBox.setValue(issue.getCategory());
        enumComboBox.setValue(issue.getIssueState());

    }


    private void buildMainLayout() {
        mainLayout = new FormLayout();
        addComponent(mainLayout);

        idField = new StTextField("Id");
        idField.setEnabled(false);
        mainLayout.addComponent(idField);

        addressArea = new TextArea("Adres:");
        mainLayout.addComponent(addressArea);

        topicField = new TextField("Konu Başlığı:");
        mainLayout.addComponent(topicField);

        contentArea = new TextArea("Konu:");
        mainLayout.addComponent(contentArea);

        dateField = new DateField("Tarih:");
        mainLayout.addComponent(dateField);

        CategoryDao categoryDao = new CategoryDao();

        categoryComboBox = new ComboBox("Kategori Seçiniz", categoryDao.findAllCategory());
        categoryComboBox.setCaption("Kategori");
        mainLayout.addComponent(categoryComboBox);



        enumComboBox = new ComboBox("Durum");
        for (EnumIssueState value : EnumIssueState.values()) {
            enumComboBox.addItem(value);
        }

        mainLayout.addComponent(enumComboBox);


        SaveButton saveButton = new SaveButton();
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                Long idFieldValue = null;
                if (idField.getValue() != "") {
                    idFieldValue = Long.parseLong(idField.getValue());
                }
                String addressAreaValue = addressArea.getValue();
                String topicFieldValue = topicField.getValue();
                String contentAreaValue = contentArea.getValue();
                Date dateFieldValue = dateField.getValue();
                Category categoryValue= (Category) categoryComboBox.getValue();
                EnumIssueState enumStateValue= (EnumIssueState) enumComboBox.getValue();


                Issue issue = new Issue();
                issue.setId(idFieldValue);
                issue.setAddress(addressAreaValue);
                issue.setTopic(topicFieldValue);
                issue.setContent(contentAreaValue);
                issue.setDate(dateFieldValue);
                issue.setCategory(categoryValue);
                issue.setIssueState(enumStateValue);


                IssueDao issueDao = new IssueDao();
                issue = issueDao.saveIssue(issue);
                idField.setValue(issue.getId().toString());


                //      EnumIssueState enumIssueState=new EnumIssueState();
                //       issueEnum.setValue(enumIssueState);


                Notification.show("İşlem Başarılı");

            }
        });
        mainLayout.addComponent(saveButton);
    }


}
