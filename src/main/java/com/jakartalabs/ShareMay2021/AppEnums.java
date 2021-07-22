package com.jakartalabs.ShareMay2021;

public class AppEnums {

	public enum MenuNames {
		LOGIN("Login"), ABOUT("About");

		private String filter;

		private MenuNames(String filter) {
			this.filter = filter;
		}

		@Override
		public String toString() {
			return filter;
		}

	}

}
