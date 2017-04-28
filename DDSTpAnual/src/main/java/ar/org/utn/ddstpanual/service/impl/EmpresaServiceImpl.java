package ar.org.utn.ddstpanual.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ar.org.utn.ddstpanual.dao.EmpresaDao;
import ar.org.utn.ddstpanual.dao.impl.EmpresaDaoImpl;
import ar.org.utn.ddstpanual.dto.EmpresaDto;
import ar.org.utn.ddstpanual.exception.DaoException;
import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.service.EmpresaService;

public class EmpresaServiceImpl implements EmpresaService {

	EmpresaDao empresaDao;

	public void subirExcel(FileInputStream file) throws ServiceException {

		List<EmpresaDto> empresas = new ArrayList<EmpresaDto>();
		EmpresaDto empresaDto = new EmpresaDto();
		Optional<EmpresaDto> optional;
		List<EmpresaDto> empresasAux;

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row;
			rowIterator.next();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();

				empresasAux = empresas;
				empresaDto.setNombreEmpresa(row.getCell(0).getStringCellValue());
				empresaDto.setNombreCuenta(row.getCell(1).getStringCellValue());
				empresaDto.setFecha(String.valueOf((float) row.getCell(2).getNumericCellValue()));
				empresaDto.setValor((float) row.getCell(3).getNumericCellValue());

				// optional = empresasAux.stream()
				// .filter(p -> p.getFecha().equals(empresaDto.getFecha())
				// && p.getNombreEmpresa().equals(empresaDto.getNombreEmpresa())
				// &&
				// p.getNombreCuenta().equals(empresaDto.getNombreCuenta())).findAny();
				optional = empresasAux.parallelStream().filter(p -> p.getNombreEmpresa().equals(empresaDto.getNombreEmpresa()))
						.findAny();
				if (empresasAux.isEmpty())
					empresas.add(empresaDto);
				else
					System.out.println("Ya existe un registro.");
			}
			getEmpresaDao().saveEmpresas(empresas);
			workbook.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	public EmpresaDao getEmpresaDao() {
		if (empresaDao != null) {
			return empresaDao;
		}
		empresaDao = new EmpresaDaoImpl();
		return empresaDao;
	}

}
