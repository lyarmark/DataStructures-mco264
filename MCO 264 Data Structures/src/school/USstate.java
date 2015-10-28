package school;

public enum USstate {
	AL("ALABAMA"), AK("ALASKA"), AZ("ARIZONA"), AR("ARKANSAS"), CA("CALIFORNIA"), CO("COLORADO"), CT("CONNECTICUT"), DE(
			"DELAWARE"), FL("FLORIDA"), GA("GEORGIA"), HI("HAWAII"), ID("IDAHO"), IL("ILLINOIS"), IN("INDIANA"), IA(
			"IOWA"), KS("KANSAS"), KY("KENTUCKY"), LA("LOUISIANA"), ME("MAINE"), MD("MARYLAND"), MA("MASSACHUSETTS"), MI(
			"MICHIGAN"), MN("MINNESOTA"), MS("MISSISSIPPI"), MO("MISSOURI"), MT("MONTANA"), NE("NEBRASKA"), NV("NEVADA"), NH(
			"NEW HAMPSHIRE"), NJ("NEW JERSEY"), NM("NEW MEXICO"), NY("NEW YORK"), NC("NORTH CAROLINA"), ND(
			"NORTH DAKOTA"), OH("OHIO"), OK("OKLAHOMA"), OR("OREGON"), PA("PENNSYLVANIA"), RI("RHODE ISLAND"), SC(
			"SOUTH CAROLINA"), SD("SOUTH DAKOTA"), TN("TENNESSEE"), TX("TEXAS"), UT("UTAH"), VA("VIRGINIA"), VT(
			"VERMONT"), WA("WASHINGTON"), WV("WEST VIRGINIA"), WI("WISCONSIN"), WY("WYOMING");

	private String stateName;

	private USstate(String name) {
		this.stateName = name;
	}

	public String getName() {
		return stateName;
	}

	public static USstate validateState(String validateState) {
		for (USstate state : USstate.values()) {
			// check by string constant and enum value
			if (state.toString().equalsIgnoreCase(validateState)
					|| (state.getName().equalsIgnoreCase(validateState))) {
				return state;
			}
		}
		// could not find my state in this country
		return null;
	}
}