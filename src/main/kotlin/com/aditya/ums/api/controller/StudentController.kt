package com.aditya.ums.api.controller

import com.aditya.ums.api.request.StudentRequest
import com.aditya.ums.api.response.Response
import com.aditya.ums.converter.StudentConverter
import com.aditya.ums.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/student")
@ResponseBody
class StudentController(
        @Autowired private val studentService: StudentService
) {
    @GetMapping("/")
    fun getStudents(): ResponseEntity<Response> {
        val students = studentService.getAll()
        val studentsResponse = Response()
                .success(true)
                .data(StudentConverter.convertToResponses(students))
                .contentType("application/json")
                .httpStatusCode(HttpStatus.OK.value()).statusMessage("success")
        return ResponseEntity(studentsResponse, HttpStatus.OK)
    }

    @PostMapping("/")
    fun postStudent(@Valid @RequestBody studentRequest: StudentRequest) : ResponseEntity<Response> {
        val student = studentService.create(studentRequest)
        val studentResponse = Response()
                .success(true)
                .data(StudentConverter.convertToResponse(student))
                .contentType("application/json")
                .httpStatusCode(HttpStatus.OK.value()).statusMessage("success")
        return ResponseEntity(studentResponse, HttpStatus.OK)
    }

    @GetMapping("/search-firstName/{firstName}")
    fun searchByFirstName(@PathVariable("firstName", required = true) firstName: String): ResponseEntity<Response>{
        val student = studentService.searchByName(firstName)
        val studentResponse = Response()
                .success(true)
                .data(StudentConverter.convertToResponse(student))
                .contentType("application/json")
                .httpStatusCode(HttpStatus.OK.value()).statusMessage("success")
        return ResponseEntity(studentResponse, HttpStatus.OK)
    }
}