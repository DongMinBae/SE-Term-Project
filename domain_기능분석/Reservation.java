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


//특정 테이블에 대한 고객의 예약의 관한 상세정보를 나타내는 클래스(Reservation)
public class Reservation extends BookingImp
{
  private Customer customer ; //예약한 고객을 나타내는 고객 객체
  private Time     arrivalTime ; //고객이 도착 해야 할 시간을 나타는 Time객체(예약 시간)
  

  //생성자
  public Reservation(int c, Date d, Time t, Table tab, Customer cust, Time arr)
  {
    super(c, d, t, tab) ;
    customer    = cust ;
    arrivalTime = arr ;
  }


  //고객의 도착 시간을 java.sql.Time 객체로 반환한다(?)
  public java.sql.Time getArrivalTime() {
    return arrivalTime ;
  }

  //예약한 고객을 나타내는 Customer 객체를 반환한다.
  public Customer getCustomer() {
    return customer ;
  }


  //고객의 이름과 전화번호, covers, 도착시간을 포함한 해당 예약의 세부 정보가 포함된 String문자열을 반환한다.
  public String getDetails()
  {
    StringBuffer details = new StringBuffer(64) ;
    details.append(customer.getName()) ;
    details.append(" ") ;
    details.append(customer.getPhoneNumber()) ;
    details.append(" (") ;
    details.append(covers) ;
    details.append(")") ;
    if (arrivalTime != null) {
      details.append(" [") ;
      details.append(arrivalTime) ;
      details.append("]") ;
    }
    return details.toString() ;
  }

  //arrivaltime필드에 대한 setter
  public void setArrivalTime(Time t) {
    arrivalTime = t ;
  }


  //customer필드에 대한 setter
  public void setCustomer(Customer c) {
    customer = c ;
  }
}
