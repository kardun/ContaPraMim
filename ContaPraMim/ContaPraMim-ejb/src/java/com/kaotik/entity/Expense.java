/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaotik.entity;

import com.google.common.base.Objects;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kaos12
 */
@Entity
@Table(name = "EXPENSE")
@NamedQueries({
    @NamedQuery(name = "Expense.findAll", query = "SELECT e FROM Expense e"),
    @NamedQuery(name = "Expense.findById", query = "SELECT e FROM Expense e WHERE e.id = :id"),
    @NamedQuery(name = "Expense.findByValueRange", query = "SELECT e FROM Expense e WHERE e.value >= :lowerValue and e.value <= :greaterValue"),
    @NamedQuery(name = "Expense.findAllByUser", query = "SELECT e FROM Expense e, User u WHERE u.id = :userId"),
    @NamedQuery(name = "Expense.findAllByCategory", query = "SELECT e FROM Expense e, Category c WHERE c.id = :categoryId"),
    @NamedQuery(name = "Expense.findAllWithinCurrentYear", query = "SELECT e FROM Expense e, User u WHERE u.id = :userId AND year(e.createdAt) = year(current_timestamp)")
})
public class Expense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", insertable = true, updatable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "NAME")
    private String name;
    @Digits(integer=6,fraction=2)
    @Min(value=1)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "VALUE")
    private BigDecimal value;
    @Size(max = 256)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "SYSUSER_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Category category;

    public Expense() {
    }

    public Expense(Integer id) {
        this.id = id;
    }

    public Expense(Integer id, String name, BigDecimal value, Date createdAt) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expense)) {
            return false;
        }
        Expense other = (Expense) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("value", value)
                .add("user", user)
                .add("category", category)
                .add("created at", createdAt)
                .toString();
    }
    
}