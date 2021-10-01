package com.example.staybooking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "stay_availability")
@JsonDeserialize(builder = StayAvailability.Builder.class) //Jakson把json转java obj时调用builder的方法构造
public class StayAvailability implements Serializable {
    private static final long serialVersionUID = 1L;

    //表示是组合键与@Embeddable成对出现
    @EmbeddedId
    private StayAvailabilityKey id;

    //stay 和 stayavailability one to many, 双方必须都是Entity
    //hibernate 会默认创建第三张表,放stay 和 stayAvail的PK stayId, stayAvailId
    //而我们只需要将stayAvailId作为一个外键即可，因此需要@JoinColumn来添加外键,如果是组合键用@MapsId，避免第三张表的生成
    @ManyToOne
    @MapsId("stay_id")
    private Stay stay;
//    private LocalDate date;

    private StayAvailabilityState state;

    public StayAvailability() {}

    public StayAvailability(Builder builder) {
        this.id = builder.id;
        this.stay = builder.stay;
        this.state = builder.state;
    }

    public StayAvailabilityKey getId() {
        return id;
    }

    public Stay getStay() {
        return stay;
    }

    public StayAvailabilityState getState() {
        return state;
    }

    public static class Builder {
        @JsonProperty("id")
        private StayAvailabilityKey id;

        @JsonProperty("stay")
        private Stay stay;

        @JsonProperty("state")
        private StayAvailabilityState state;

        public Builder setId(StayAvailabilityKey id) {
            this.id = id;
            return this;
        }

        public Builder setStay(Stay stay) {
            this.stay = stay;
            return this;
        }

        public Builder setState(StayAvailabilityState state) {
            this.state = state;
            return this;
        }

        public StayAvailability build() {
            return new StayAvailability(this);
        }
    }



}


//关系型数据库特性：ACID
//Atomic原子性
//Consistency一致性（table 和 table之间的关系一致）
//