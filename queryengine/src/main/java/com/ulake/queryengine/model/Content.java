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
@Table(name="content")
@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cid", nullable = false)
    private String cid;

    @Column(name = "type")
    private String type;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<JsonNode> extra;

    public Content() {
    }

    public Content(String cid) {
        this.cid = cid;
    }

    public Content(String type, List<JsonNode> extra) {
        this.type = type;
        this.extra = extra;
    }

    public String getCid() {
        return cid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<JsonNode> getExtra() {
        return extra;
    }

    public void setExtra(List<JsonNode> extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "Content{" +
                "cid='" + cid + '\'' +
                ", type='" + type + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
