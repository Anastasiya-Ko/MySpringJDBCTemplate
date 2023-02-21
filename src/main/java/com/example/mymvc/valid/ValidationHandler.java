package com.example.mymvc.valid;

//@ControllerAdvice
//public class ValidationHandler extends ResponseEntityExceptionHandler {
//
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//
//            Map<String, String> errors = new HashMap<>();
//            ex.getBindingResult().getAllErrors().forEach((error) -> {
//
//                String fieldName = ((FieldError) error).getField();
//                String message = error.getDefaultMessage();
//                String errorMessage = fieldName + " " + message;
//                errors.put(errorMessage, message);
//            });
//        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//    }
//
//}
