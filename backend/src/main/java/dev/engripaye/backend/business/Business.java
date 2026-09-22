package dev.engripaye.backend.business;

import jakarta.persistence.*;

@Entity
@Table(name = "business")
public class Business {

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String slug;

    @Column(nullable=false)
    private String currency = "NGN";

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="owner_id", nullable=false)
    private AppUser owner;

    protected Business(){}

    public Business(String name, String slug, AppUser owner)
    {this.name=name;this.slug=slug;this.owner=owner;}

    public String getName()
    {return name;}

    public String getSlug()
    {return slug;}

    public String getCurrency()
    {return currency;}

}
