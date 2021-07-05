package com.ulake.queryengine.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="dataset")
@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Dataset {
    //not implementing default here...
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "file")
    private String file;

    @Column(name = "description")
    private String description;

    @Column(name = "source")
    private String source;

    @Column(name = "topics")
    @ElementCollection(targetClass=String.class)
    private List<String> topics;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<JsonNode> extra;

    @Column(name = "parent")
    private Long parent;

    @ManyToOne
    @JoinColumn(name = "file", nullable = false, insertable=false, updatable=false)
    private Content content;


    public Dataset() {
    }

    public Dataset(Long id) {
        this.id = id;
    }

    public Dataset(String file, String description, String source, List<String> topics, List<JsonNode> extra, Long parent) {
        this.file = file;
        this.description = description;
        this.source = source;
        this.topics = topics;
        this.extra = extra;
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<JsonNode> getExtra() {
        return extra;
    }

    public void setExtra(List<JsonNode> extra) {
        this.extra = extra;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Dataset{" +
                "id=" + id +
                ", file='" + file + '\'' +
                ", description='" + description + '\'' +
                ", source='" + source + '\'' +
                ", topics=" + topics +
                ", extra='" + extra + '\'' +
                ", parent=" + parent +
                '}';
    }
}
