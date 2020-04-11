package com.xyz.scalatest.service

import cn.gjing.tools.common.result.PageResult
import com.xyz.scalatest.entity.Customer
import com.xyz.scalatest.exceptions.MyServiceException
import com.xyz.scalatest.repository.CustomerRepository
import javax.annotation.Resource
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService @Resource()(customerRepository: CustomerRepository) {
  /**
    * 保存用户
    *
    * @param name 用户名
    */
  def saveCustomer(name: String): Unit = {
    var customer = customerRepository.findByCustomerName(name)
    if (customer != null) {
      throw MyServiceException("添加失败，用户已存在")
    }
    customer = new Customer(name)
    customerRepository.save(customer)
  }

  /**
    * 分页查询
    *
    * @param pageable 分页对象
    * @return
    */
  def pageCustomer(pageable: Pageable): PageResult[java.util.List[Customer]] = {
    val page = customerRepository.findAll(pageable)
    return PageResult.of(page.getContent, page.getTotalPages, page.getSize, page.getTotalElements, page.getNumber)
  }

  /**
    * 更新用户名
    * @param id 用户id
    * @param name 用户名
    */
  def updateCustomer(id: Integer, name: String): Unit = {
    val customer = customerRepository.findById(id).orElseThrow(() => MyServiceException("更新失败，用户不存在"))
    customer.setCustomerName(name)
    customerRepository.saveAndFlush(customer)
  }

  /**
    * 删除指定用户
    * @param id 用户id
    */
  def deleteCustomer(id:Integer): Unit = {
    val customer = customerRepository.findById(id).orElseThrow(() => MyServiceException("删除失败，用户不存在"))
    customerRepository.delete(customer)
  }

}
