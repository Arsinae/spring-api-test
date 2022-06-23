package com.arsinae.apitest.model.datecount;

import java.sql.Date;

public class DateCount {
  
  private Date date;
  private Long count;

  public DateCount(String dateString, Long count) {
    this.date = Date.valueOf(dateString);
    this.count = count;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }
}
