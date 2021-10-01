package com.example.staybooking.model;

import java.time.LocalDate;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

//组合键专用annotation
@Embeddable
public class StayAvailabilityKey implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long stay_id;
    private LocalDate date;

    //为了让hibernate帮助创建对象和管理对象
    public StayAvailabilityKey() {}

    public StayAvailabilityKey(Long stay_id, LocalDate date) {
        this.stay_id = stay_id;
        this.date = date;
    }

    public Long getStay_id() {
        return stay_id;
    }

    public StayAvailabilityKey setStay_id(Long stay_id) {
        this.stay_id = stay_id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public StayAvailabilityKey setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    //key需要进行比较，最好override一下equal，确保是自己想要的比较方式
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StayAvailabilityKey that = (StayAvailabilityKey) o;
        return stay_id.equals(that.stay_id) && date.equals(that.date);
    }

    //hashcode在放入map时， 用来判断两个相同的stayid date pair是否同一个pair
    @Override
    public int hashCode() {
        return Objects.hash(stay_id, date);
    }
}
