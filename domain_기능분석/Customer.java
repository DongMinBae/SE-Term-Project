/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.domain ;


//고객 인스턴스를 정의하는 클래스
public class Customer
{
  private String name ; //이름
  private String phoneNumber ; //핸드폰 번호


  //생성자
  public Customer(String n, String p)
  {
    name = n ;
    phoneNumber = p ;
  }
  //이름 필드에 대한 getter
  public String getName()
  {
    return name ;
  }


  //핸드폰 번호 필드에 대한 getter
  public String getPhoneNumber()
  {
    return phoneNumber ;
  }
}
