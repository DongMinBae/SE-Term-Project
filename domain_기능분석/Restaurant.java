/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.domain ;

import booksys.application.persistency.* ;

import java.sql.Date ;
import java.sql.Time ;
import java.util.Vector ;


//예약, 고객 및 테이블에 접근하고 수정하는 메서드를 제공하는 클래스
class Restaurant
{
  //아래 세 필드 모두 Singlton디자인 패턴을 사용하여 이러한 클래스의 인스턴스 하나만 만들어서 전체에 적용되도록 한다.
  BookingMapper  bm = BookingMapper.getInstance() ; 
  CustomerMapper cm = CustomerMapper.getInstance() ;
  TableMapper    tm = TableMapper.getInstance() ;
  
  //Date타임의 매개변수를 받고 해당 날짜에 대한 벡터를 반환한다.
  Vector getBookings(Date date)
  {
    return bm.getBookings(date) ;
  }

  //이름과 핸드폰 번호를 문자열 형태의 매개변수로 받고 해당 Customer객체를 반환한다.
  Customer getCustomer(String name, String phone)
  {
    return cm.getCustomer(name, phone) ;
  }
  

  //테이블 넘버 즉, 테이블 번호를 int형 매개변수로 받고 테이블 객체를 반환한다.
  Table getTable(int n)
  {
    return tm.getTable(n) ;
  }

  //현재 사용 가능한 테이블 번호의 벡터를 반환한다.
  static Vector getTableNumbers()
  {
    return TableMapper.getInstance().getTableNumbers() ;
  }


  //cover, 날짜, 시간, 테이블 번호, 고객이름, 고객 전화번호를 인수로 받고 새로운 하나의 예약을 생성한다(makeReservation).
  public Booking makeReservation(int covers, Date date,
				     Time time,
				     int tno, String name, String phone)
  {
    Table t = getTable(tno) ; //1. getTable메서드와 
    Customer c = getCustomer(name, phone) ; //2. getCustomer메서드를 사용하여 해당 테이블과 해당 고객을 검색하여 
    return bm.createReservation(covers, date, time, t, c, null) ; //3. createReservation메서드에 전달해서 새로운 예약을 생성한다.
  }



  public Booking makeWalkIn(int covers, Date date,
			   Time time, int tno)
  {
    Table t = getTable(tno) ;
    return bm.createWalkIn(covers, date, time, t) ;
  }


  //Booking개체를 매개변수로 사용하여 예약을 최신화 한다.
  public void updateBooking(Booking b)
  {
    bm.updateBooking(b) ;
  }
  
  //Booking개체를 매개변수로 사용하여 해당 예약을 삭제한다.
  public void removeBooking(Booking b) {
    bm.deleteBooking(b) ;
  }
}
