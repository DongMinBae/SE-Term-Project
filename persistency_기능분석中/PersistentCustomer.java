/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package booksys.application.persistency ;

import booksys.application.domain.Customer;

class PersistentCustomer extends Customer
{
  private int oid ;
  // 정수 id와 이름 n, 핸드폰 번호 p를 이용하여 조상클래스 생성자를 호출한다.
  // 이름과 전화번호로 고객 객체를 생성한 다음 oid필드를 주어진 ID로 설정한다.
  PersistentCustomer(int id, String n, String p)
  {
    super(n, p) ;
    oid = id ;
  }
  //getId메서드는 데이터베이스에서 고객 객체를 식별하는데 사용한다.
  int getId() {
    return oid ;
  }
}
