package ar.org.utn.ddstpanual.exception;

import org.apache.commons.lang3.StringUtils;


public class ServiceException extends Exception {

  private static final long serialVersionUID = 7089689275742164242L;
  private String code;

  public ServiceException(final String mensaje) {
    super(mensaje);
  }

  public ServiceException(final String mensaje, final String code) {
    super(mensaje);
    this.code = code;
  }

  public ServiceException(final String mensaje, final Exception ex) {
    super(mensaje, ex);
  }

  public ServiceException(final String mensaje, final String code, final Exception ex) {
    super(mensaje, ex);
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public void setCode(final String code) {
    this.code = code;
  }

  public boolean hasErrorCode() {
    return !StringUtils.isBlank(getCode());
  }
}
