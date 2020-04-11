package com.xyz.scalatest.exceptions

/**
 * @author Gjing
 **/
case class MyServiceException(message:String) extends RuntimeException(message)
