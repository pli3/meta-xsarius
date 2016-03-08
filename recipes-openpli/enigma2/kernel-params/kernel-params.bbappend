do_install_append() {
	echo "vm.min_free_kbytes = 8192" >> ${D}${sysconfdir}/sysctl.conf
	echo "vm.swappiness = 0" >> ${D}${sysconfdir}/sysctl.conf
}
