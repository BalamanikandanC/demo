package com.example.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public int id;
	private String name;
	private String email;
	private int experience;
	private String domain;
}
