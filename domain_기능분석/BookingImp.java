/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.domain ;

import java.sql.Date ;
import java.sql.Time ;

public abstract class BookingImp implements Booking
{
  protected int   covers ;
  protected Date  date ;
  protected Time  time ;
  protected Table table ;
  
  public BookingImp(int c, Date d, Time t, Table tab) {
    covers    = c ;
    date      = d ;
    time = t ;
    table     = tab ;
  }
 
  //Booking인터페이스에서 구현한 추상 메서드를 구현한 부분(예약 도착 시간)
  public Time getArrivalTime() {
    return null ;
  }
  //cover 필드의 getter
  public int getCovers() {
    return covers;
  }
  //date 필드의 getter
  public Date getDate() {
    return date;
  }

  // End time defaults to 2 hours after time of booking
  
  //Booking인터페이스에서 구현한 추상 메서드를 구현한 부분(예약 종료 시간)
  public Time getEndTime() {
    Time endTime = (Time) time.clone() ;
    endTime.setHours(endTime.getHours() + 2) ;
    return endTime ;
  }
  
  //time 필드의 getter
  public Time getTime() {
    return time;
  }
  
  //table 필드의 getter
  public Table getTable() {
    return table;
  }

  //Booking인터페이스에서 구현한 추상 메서드를 구현한 부분(예약된 테이블 번호)
  public int getTableNumber() {
    return table.getNumber() ;
  }
  
  //Booking인터페이스에서 구현한 추상 메서드를 구현한 부분(예약 도착 시간)
  public void setArrivalTime(Time t) { }


  //cover필드의 setter
  public void setCovers(int c) {
    covers = c ;
  }

  //date필드의 setter
  public void setDate(Date d) {
    date = d ;
  }

  //time필드의 setter
  public void setTime(Time t) {
    time = t ;
  }
  
  //table필드의 setter
  public void setTable(Table t) {
    table = t ;
  }
}
