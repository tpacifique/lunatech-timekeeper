/*
 * Copyright 2020 Lunatech S.A.S
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.lunatech.timekeeper.resources.utils;

/**
 * A Simple utility class that is used to catch RESTEasy HTTP error and convert the result into
 * a simple RuntimeException
 */
public class HttpTestRuntimeException extends RuntimeException{
    private Integer httpStatus;
    private String httpMessage;
    private String mimeType;

    public HttpTestRuntimeException(Integer httpStatus, String httpMessage, String mimeType) {
        this.httpStatus = httpStatus;
        this.httpMessage = httpMessage;
        this.mimeType = mimeType;
    }

    public HttpTestRuntimeException(String message, Integer httpStatus, String httpMessage, String mimeType) {
        super(message);
        this.httpStatus = httpStatus;
        this.httpMessage = httpMessage;
        this.mimeType = mimeType;
    }

    public HttpTestRuntimeException(String message, Throwable cause, Integer httpStatus, String httpMessage, String mimeType) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.httpMessage = httpMessage;
        this.mimeType = mimeType;
    }

    public HttpTestRuntimeException(Throwable cause, Integer httpStatus, String httpMessage, String mimeType) {
        super(cause);
        this.httpStatus = httpStatus;
        this.httpMessage = httpMessage;
        this.mimeType = mimeType;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public String getHttpMessage() {
        return httpMessage;
    }

    public String getMimeType() {
        return mimeType;
    }
}
