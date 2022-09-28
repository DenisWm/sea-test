package br.com.sea.tests;


import br.com.sea.dto.RoleDTO;
import br.com.sea.dto.SectorDTO;
import br.com.sea.dto.WorkerDTO;
import br.com.sea.entities.Role;
import br.com.sea.entities.Sector;
import br.com.sea.entities.Worker;

public class Factory {
	
	public static Sector createSector() {
		Sector sector = new Sector(1, "Calçados");
		return sector;
	}
	
	public static SectorDTO createSectorDTO() {
		Sector sector = createSector();
		return new SectorDTO(sector);
	}

	public static Role createRole() {
		Role role = new Role(1, "Cosméticos", createSector());
		return role;
	}

	public static RoleDTO createRoleDTO() {
		Role role = createRole();
		return new RoleDTO(role);
	}
	public static Worker createWorker() {
		Worker worker = new Worker(createSector(), createRole(), "Caradomes", "31917208049");
		return worker;
	}

	public static WorkerDTO createWorkerDTO() {
		Worker worker = createWorker();
		return new WorkerDTO(worker);
	}
}
