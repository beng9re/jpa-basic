package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true,length = 10)
    private String name;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @ManyToMany
    @JoinTable(name="mamberTable")
    private List<Product> product;

    @ManyToMany
    @JoinTable(name = "Member_member",
        joinColumns = @JoinColumn(name = "member_id", referencedColumnName = "member_id"))
    private List<Product> member;

    public List<Product> getMember() {
        return member;
    }

    public void setMember(List<Product> member) {
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

