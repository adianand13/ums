package com.aditya.ums.converter

import com.aditya.ums.api.request.BatchRequest
import com.aditya.ums.api.response.BatchResponse
import com.aditya.ums.entity.Batch

class BatchConverter {
    companion object{
        fun convertToResponses(batches: List<Batch>): List<BatchResponse>{
            return  batches.map { batch -> convertToResponse(batch) }
        }
        fun convertToEntity(batchRequest: BatchRequest): Batch{
            return Batch(
                name = batchRequest.name,
                description = batchRequest.description
            )
        }

        fun convertToResponse(batch: Batch): BatchResponse{
            return BatchResponse(
                    id = batch.id,
                    name = batch.name,
                    description = batch.description,
                    semesters = SemesterConverter.convertToResponses(batch.semesters)
            )
        }
    }
}