# Spring Boot Job Portal

Live demo: [https://springboot-job-portal-production.up.railway.app](https://springboot-job-portal-production.up.railway.app)

## Overview

A modern Job Portal built with Spring Boot that enables users to search for jobs, apply online, and manage their applications. Admins can manage postings and oversee user activity.

## Screenshots

_Screenshots coming soon_

## Technologies Used

- Spring Boot 3
- Spring Security
- Hibernate/JPA 
- MySQL 
- Thymeleaf / HTML, CSS
- JWT / OAuth2 (if applicable)
- Railway / Docker for MySQL database server and a separate server for the SpringBoot application
- Maven

## Features

- User registration and login
- User profile settings based on user type (Recruiter/Job Seeker)
- Role-based access (Recruiter, Job Seeker)
- Job search and filters
- Application tracking
- Admin dashboard for job management
  


## Installation

```bash
git clone https://github.com/your-username/springboot-job-portal.git
cd springboot-job-portal
./mvnw spring-boot:run
