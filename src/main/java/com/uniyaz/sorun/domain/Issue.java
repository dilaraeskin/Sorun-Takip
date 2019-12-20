    package com.uniyaz.sorun.domain;

import com.uniyaz.sorun.common.BaseDomain;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import java.util.Date;

@Entity
@Table(name = "ISSUE")
public class Issue extends BaseDomain {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 255)
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(length = 100)
    private String topic;

    @Column(length = 500)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private EnumIssueState issueState;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CATEGORY", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "ISSUE_CATEGORY_ID"))
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EnumIssueState getIssueState() {
        return issueState;
    }

    public void setIssueState(EnumIssueState issueState) {
        this.issueState = issueState;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}