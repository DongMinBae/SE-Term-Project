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

/* This needs to be an interface so that PersistentBooking can be
   an interface, and so implemented by PersistentReservation and
   PersistentWalkin which already extend Reservation and Walkin.
*/


//예약 인터페이스 - 레스토랑 시스템의 모든 예약(예약 또는 워크인)이 따라야 하는 추상메서드를 정의하는 부분.
public interface Booking
{
  public Time  getArrivalTime() ; //예약 도착 시간을 Time 객체로 반환한다.
  public int   getCovers() ; //예약에 대한 수를 int형으로 반환한다.
  public Date  getDate() ; //예약 날짜를 Date 객체로 반환한다.
  public Time  getEndTime() ; //예약 종료시간을 Time객체로 반환한다.
  public Time  getTime() ; //예약 시작 시간을 Time객체로 반환한다.
  public Table getTable() ; //예약된 테이블 객체를 반환한다.
  public int   getTableNumber() ; //예약된 테이블 번호를 반환한다.
  
  public String getDetails() ; //예약에 관한 세부 정보를 반환한다.
  
  public void setArrivalTime(Time t) ; //예약 도착 시간을 Time객체로 설정한다.
  public void setCovers(int c) ; //??
  public void setDate(Date d) ; //예약 날짜를 주어진 Time객체로 설정한다.
  public void setTime(Time t) ; //예약 시작 시간을 주어진 Time객체로 설정한다.
  public void setTable(Table t) ; //예약에 해당하는 Table객체를 주어진 Table객체로 설정한다.
}
