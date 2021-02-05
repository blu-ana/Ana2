package com.alejandro.ana.pojos;

public class MethodManager {

	private boolean methodFindByOrLoop; // method find for tipe or neme or atributed 
	
	private boolean methodfindById;
	
	private boolean metohdSave;
	
	private boolean methodgetAll;
	
	private boolean methodDelete;
	
	private boolean methodUpdate;
	
	private boolean methodsaveOrUpdate; 
	
	private boolean methodContaining; // metodo contain de atributos de la clase
	
	private boolean methodContainingRelacion; // si hay relacion 
	
	private boolean methodContainingRelacionNoBiDirectional; // si hay relacion bideireccional

	
	public MethodManager() { }
	
	
	public MethodManager(boolean defaultValue) { 
		this.validDefault(defaultValue);
	}
	

	public void validDefault(boolean defaultValue) {	
		//if(defaultValue) {		
			this.methodFindByOrLoop = true; 
			this.methodfindById= true;
			this.metohdSave= true;
			this.methodgetAll= true;						
			this.methodUpdate= true;			
			this.methodsaveOrUpdate= true; 			
			this.methodContaining= true; 			
			this.methodContainingRelacion= true; 			
			this.methodContainingRelacionNoBiDirectional= true; 
		// }
	}
	

	public boolean isMethodFindByOrLoop() {
		return methodFindByOrLoop;
	}

	public void setMethodFindByOrLoop(boolean methodFindByOrLoop) {
		this.methodFindByOrLoop = methodFindByOrLoop;
	}

	public boolean isMethodfindById() {
		return methodfindById;
	}

	public void setMethodfindById(boolean methodfindById) {
		this.methodfindById = methodfindById;
	}

	public boolean isMetohdSave() {
		return metohdSave;
	}

	public void setMetohdSave(boolean metohdSave) {
		this.metohdSave = metohdSave;
	}

	public boolean isMethodgetAll() {
		return methodgetAll;
	}

	public void setMethodgetAll(boolean methodgetAll) {
		this.methodgetAll = methodgetAll;
	}

	public boolean isMethodDelete() {
		return methodDelete;
	}

	public void setMethodDelete(boolean methodDelete) {
		this.methodDelete = methodDelete;
	}

	public boolean isMethodUpdate() {
		return methodUpdate;
	}

	public void setMethodUpdate(boolean methodUpdate) {
		this.methodUpdate = methodUpdate;
	}

	public boolean isMethodsaveOrUpdate() {
		return methodsaveOrUpdate;
	}

	public void setMethodsaveOrUpdate(boolean methodsaveOrUpdate) {
		this.methodsaveOrUpdate = methodsaveOrUpdate;
	}

	public boolean isMethodContaining() {
		return methodContaining;
	}

	public void setMethodContaining(boolean methodContaining) {
		this.methodContaining = methodContaining;
	}

	public boolean isMethodContainingRelacion() {
		return methodContainingRelacion;
	}

	public void setMethodContainingRelacion(boolean methodContainingRelacion) {
		this.methodContainingRelacion = methodContainingRelacion;
	}

	public boolean isMethodContainingRelacionNoBiDirectional() {
		return methodContainingRelacionNoBiDirectional;
	}

	public void setMethodContainingRelacionNoBiDirectional(boolean methodContainingRelacionNoBiDirectional) {
		this.methodContainingRelacionNoBiDirectional = methodContainingRelacionNoBiDirectional;
	}

	
	
	@Override
	public String toString() {
		return "MethodManager [methodFindByOrLoop=" + methodFindByOrLoop + ", methodfindById=" + methodfindById
				+ ", metohdSave=" + metohdSave + ", methodgetAll=" + methodgetAll + ", methodDelete=" + methodDelete
				+ ", methodUpdate=" + methodUpdate + ", methodsaveOrUpdate=" + methodsaveOrUpdate
				+ ", methodContaining=" + methodContaining + ", methodContainingRelacion=" + methodContainingRelacion
				+ ", methodContainingRelacionNoBiDirectional=" + methodContainingRelacionNoBiDirectional + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (methodContaining ? 1231 : 1237);
		result = prime * result + (methodContainingRelacion ? 1231 : 1237);
		result = prime * result + (methodContainingRelacionNoBiDirectional ? 1231 : 1237);
		result = prime * result + (methodDelete ? 1231 : 1237);
		result = prime * result + (methodFindByOrLoop ? 1231 : 1237);
		result = prime * result + (methodUpdate ? 1231 : 1237);
		result = prime * result + (methodfindById ? 1231 : 1237);
		result = prime * result + (methodgetAll ? 1231 : 1237);
		result = prime * result + (methodsaveOrUpdate ? 1231 : 1237);
		result = prime * result + (metohdSave ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MethodManager other = (MethodManager) obj;
		if (methodContaining != other.methodContaining)
			return false;
		if (methodContainingRelacion != other.methodContainingRelacion)
			return false;
		if (methodContainingRelacionNoBiDirectional != other.methodContainingRelacionNoBiDirectional)
			return false;
		if (methodDelete != other.methodDelete)
			return false;
		if (methodFindByOrLoop != other.methodFindByOrLoop)
			return false;
		if (methodUpdate != other.methodUpdate)
			return false;
		if (methodfindById != other.methodfindById)
			return false;
		if (methodgetAll != other.methodgetAll)
			return false;
		if (methodsaveOrUpdate != other.methodsaveOrUpdate)
			return false;
		if (metohdSave != other.metohdSave)
			return false;
		return true;
	}
	
	
	
	
	
}
