package com.aditya.ums.api.controller

import com.aditya.ums.api.request.SectionRequest
import com.aditya.ums.api.request.SubjectRequest
import com.aditya.ums.api.response.Response
import com.aditya.ums.api.response.SemesterResponse
import com.aditya.ums.converter.SemesterConverter
import com.aditya.ums.service.SemesterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@ResponseBody
@RequestMapping("/api/semester")
class SemesterController (
        @Autowired private val semesterService: SemesterService
){
    //refactor nomenclature
    @PostMapping("/{semesterId}/add-section")
    fun addSection(
            @PathVariable("semesterId", required = true) semesterId:Int,
            @Valid @RequestBody sectionRequest: SectionRequest
    ): ResponseEntity<Response> {
        val semester = SemesterConverter.convertToResponse(semesterService.addSection(semesterId, sectionRequest))
        val semesterResponse = Response()
                .success(true)
                .data(semester)
                .contentType("/application/json")
                .httpStatusCode(HttpStatus.OK.value())
                .statusMessage("Success")
        return ResponseEntity(semesterResponse, HttpStatus.OK)
    }

    @PatchMapping("/{semesterId}/activate")
    fun activateSection(@PathVariable("semesterId", required = true) semesterId:Int): ResponseEntity<Response> {
        val semesterResponse = SemesterConverter.convertToResponse(semesterService.activate(semesterId))
        val response = Response()
                .success(true)
                .data(semesterResponse)
                .contentType("/application/json")
                .httpStatusCode(HttpStatus.OK.value())
                .statusMessage("Success")
        return ResponseEntity(response, HttpStatus.OK)
    }

    //refactor subject code
    @PostMapping("/{semesterId}/add-subject")
    fun addSubject(
            @PathVariable("semesterId", required = true) semesterId:Int,
            @Valid @RequestBody subjectRequest: SubjectRequest
    ): ResponseEntity<Response> {
        val semester = SemesterConverter.convertToResponse(semesterService.addSubject(semesterId, subjectRequest))
        val semesterResponse = Response()
                .success(true)
                .data(semester)
                .contentType("/application/json")
                .httpStatusCode(HttpStatus.OK.value())
                .statusMessage("Success")
        return ResponseEntity(semesterResponse, HttpStatus.OK)
    }

    @GetMapping("/{semesterId}")
    fun getById(@PathVariable("semesterId", required = true) semesterId:Int): ResponseEntity<Response> {
        val semester : SemesterResponse = SemesterConverter.convertToResponse(semesterService.getById(semesterId))
        val semesterResponse = Response()
                .success(true)
                .data(semester)
                .contentType("application/json")
                .httpStatusCode(HttpStatus.OK.value()).statusMessage("success")
        return ResponseEntity(semesterResponse, HttpStatus.OK)
    }
}