package com.tuituidan.openhub.bean.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Setting.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2021/9/27 0027
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "nav_email_setting", schema = "team_nav")
@DynamicInsert
@DynamicUpdate
public class EmailSetting implements Serializable {

    private static final long serialVersionUID = 7585213178024403051L;

    @Id
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "host", length = 100)
    private String host;

    @Column(name = "protocol", length = 100)
    private String protocol;

    @Column(name = "port")
    private Integer port;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

}
