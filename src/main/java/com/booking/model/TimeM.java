package com.booking.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class TimeM {
        @Id
        private String id;
        @JsonView(Views.Public.class)
        private Integer hour;
        @JsonView(Views.Public.class)
        private Integer minute;

}
