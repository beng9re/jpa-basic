package jpa;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
public class Member {

    @Id
    private Long id;

    @Column(unique = true,length = 10)
    private String name;

    private Integer age;

    //기본타입은 EnumType.ORDINAL 사용하지 않는다 오류의 소지가 가능
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String decription;

    //DDL을 생성되지않음
    @Transient
    private int temp;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
