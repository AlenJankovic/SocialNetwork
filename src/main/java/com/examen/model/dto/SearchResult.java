package com.examen.model.dto;

import java.util.Set;

import com.examen.model.entity.Interest;
import com.examen.model.entity.Profile;

public class SearchResult {											//information safe to expose to web layer
		private Long userId;
		private String firstName;
		private String lastName;
		private Set<Interest> interests;
		
		public SearchResult(Profile profile) {
			userId = profile.getUser().getId();
			firstName= profile.getUser().getFirstname();
			lastName = profile.getUser().getLastname();
			interests = profile.getInterests();
		}

		public Long getUserId() {
			return userId;
		}

		
		public String getFirstName() {
			return firstName;
		}

		
		public String getLastName() {
			return lastName;
		}

		
		public Set<Interest> getInterests() {
			return interests;
		}

		
		public void setUserId(Long userId) {
			this.userId = userId;
		}

		
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		
		public void setInterests(Set<Interest> interests) {
			this.interests = interests;
		}

		@Override
		public String toString() {
			return "SearchResult [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", interests=" + interests + "]";
		}
		
}
