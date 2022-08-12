package db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "test_user")
@UserDefinition
public class UserEntity extends PanacheEntity {
    @Username
    public String username;
    @Password
    public String password;
    @Roles
    public String role;

    @Column(name = "user_daily_ad_count")
    public int dailyAdCount;

    @Column(name = "user_weekly_ad_count")
    public int weeklyAdCount;

    @Column(name = "user_monthly_ad_count")
    public int monthlyAdCount;

    @Column(name = "user_yearly_ad_count")
    public int yearlyAdCount;

    @Column(name = "user_year_to_date_ad_count")
    public int yearToDateAdCount;
}
