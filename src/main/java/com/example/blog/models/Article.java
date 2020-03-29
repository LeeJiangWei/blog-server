package com.example.blog.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Article {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "文章标题不能为空")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(columnDefinition = "LONGTEXT")
    private String body;

    public Article() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
