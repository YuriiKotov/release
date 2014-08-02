package project.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yurii on 7/24/14.
 */
@Entity
@Table(name = "TAXI_ORDER")
public class Order {
    @Id
    @SequenceGenerator(name = "sequence", sequenceName = "Taxi_Order_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")

    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "ORDER_DATE")
    private Date order_date;

    @ManyToOne
    private Client client = new Client();

    @Column(name = "COST")
    private Integer cost;

    @Column(name = "ADDRESS_START")
    private String start;

    @Column(name = "ADDRESS_FINISH")
    private String finish;

    public Order() {
    }

    public Order(Date order_date, Client client, Integer cost, String start, String finish) {
        this.order_date = order_date;
        this.client = client;
        this.cost = cost;
        this.start = start;
        this.finish = finish;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", order_date=" + order_date +
                ", client=" + client +
                ", cost=" + cost +
                ", start='" + start + '\'' +
                ", finish='" + finish + '\'' +
                '}';
    }
}


