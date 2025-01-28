package com.opt.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@JsonInclude(value = Include.NON_NULL)
public class UserDTO implements Serializable {

	/**
	 * @author Santhanakumar.v
	 */
	private static final long serialVersionUID = 1L;

	private String userId;

	@NotBlank(message = "User name cannot be blank")
	private String userName;

	@NotBlank(message = "Name cannot be blank")
	private String name;

	@NotBlank(message = "Enter vaid email id")
	@Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	private String email;

	@NotBlank(message = "Select gender male/female")
	private String gender;

	private String picture;

}
