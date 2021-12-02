package lv.kalinins.demoapi.domain;

import lv.kalinins.demoapi.domain.enums.UserType;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Contract> contracts;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType type;

    public long getId() {
        return id;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
