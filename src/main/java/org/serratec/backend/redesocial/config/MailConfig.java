package org.serratec.backend.redesocial.config;

public class MailConfig {
	private String smtpHost;
	private int smtpPort;
	private String username;
	private String password;
	private boolean sslEnabled;
	private String emailRemetente;
	private String emailSuporte;

	public MailConfig(String smtpHost, int smtpPort, String username, String password, boolean sslEnabled,
			String emailRemetente, String emailSuporte) {
		this.smtpHost = smtpHost;
		this.smtpPort = smtpPort;
		this.username = username;
		this.password = password;
		this.sslEnabled = sslEnabled;
		this.emailRemetente = emailRemetente;
		this.emailSuporte = emailSuporte;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isSslEnabled() {
		return sslEnabled;
	}

	public void setSslEnabled(boolean sslEnabled) {
		this.sslEnabled = sslEnabled;
	}

	public String getEmailRemetente() {
		return emailRemetente;
	}

	public void setEmailRemetente(String emailRemetente) {
		this.emailRemetente = emailRemetente;
	}

	public String getEmailSuporte() {
		return emailSuporte;
	}

	public void setEmailSuporte(String emailSuporte) {
		this.emailSuporte = emailSuporte;
	}

	@Override
	public String toString() {
		return "MailConfig{" + "smtpHost='" + smtpHost + '\'' + ", smtpPort=" + smtpPort + ", username='" + username
				+ '\'' + ", sslEnabled=" + sslEnabled + ", emailRemetente='" + emailRemetente + '\''
				+ ", emailSuporte='" + emailSuporte + '\'' + '}';
	}

}
