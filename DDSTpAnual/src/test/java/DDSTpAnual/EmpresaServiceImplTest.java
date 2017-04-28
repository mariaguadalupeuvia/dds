package DDSTpAnual;

import org.junit.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import ar.org.utn.ddstpanual.exception.ServiceException;
import ar.org.utn.ddstpanual.service.impl.EmpresaServiceImpl;

public class EmpresaServiceImplTest {
	File file = null;
	FileInputStream fileStream = null;
	EmpresaServiceImpl service = null;
	
	@Before
	public void init() throws FileNotFoundException {
		file = new File("C:\\Users\\Compumar\\git\\2017-vn-group-24\\DDSTpAnual\\src\\main\\resources\\Carga1.xlsx");
		fileStream = new FileInputStream(file);
		service = new EmpresaServiceImpl();
	}

	@Test 
	public void testSubirExcel() {
		try {
			service.subirExcel(fileStream);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@After
	public void finish (){
	
		try {
			if (fileStream != null)
				fileStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}

}
