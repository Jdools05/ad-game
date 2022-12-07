package db.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "test_user")
@UserDefinition
public class UserEntity extends PanacheEntity {
    @Username
    public String username;
    @Password
    public String password;
    @Email
    public String email;
    @Roles
    public String role;

    @Column(name = "user_verified")
    public boolean verified = false;

    @Column(name = "user_daily_ad_count")
    public int dailyAdCount;

    @Column(name = "user_weekly_ad_count")
    public int weeklyAdCount;

    @Column(name = "user_monthly_ad_count")
    public int monthlyAdCount;

    @Column(name = "user_all_time_ad_count")
    public int allTimeAdCount;

    @Column(name = "user_year_to_date_ad_count")
    public int yearToDateAdCount;

    public int getDailyAdCount() {
        return dailyAdCount;
    }

    public void setDailyAdCount(int dailyAdCount) {
        this.dailyAdCount = dailyAdCount;
    }

    public int getWeeklyAdCount() {
        return weeklyAdCount;
    }

    public void setWeeklyAdCount(int weeklyAdCount) {
        this.weeklyAdCount = weeklyAdCount;
    }

    public int getMonthlyAdCount() {
        return monthlyAdCount;
    }

    public void setMonthlyAdCount(int monthlyAdCount) {
        this.monthlyAdCount = monthlyAdCount;
    }

    public int getAllTimeAdCount() {
        return allTimeAdCount;
    }

    public void setAllTimeAdCount(int allTimeAdCount) {
        this.allTimeAdCount = allTimeAdCount;
    }

    public int getYearToDateAdCount() {
        return yearToDateAdCount;
    }

    public void setYearToDateAdCount(int yearToDateAdCount) {
        this.yearToDateAdCount = yearToDateAdCount;
    }

    public void incrementAdCount() {
        dailyAdCount += 1;
        weeklyAdCount += 1;
        monthlyAdCount += 1;
        allTimeAdCount += 1;
        yearToDateAdCount += 1;
    }

    public void resetDailyAdCount() {
        dailyAdCount = 0;
    }

    public void resetWeeklyAdCount() {
        weeklyAdCount = 0;
    }

    public void resetMonthlyAdCount() {
        monthlyAdCount = 0;
    }

    public void resetYearlyAdCount() {
        yearToDateAdCount = 0;
    }
}
