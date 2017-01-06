require xsarius-initrd.inc

do_install() {
    install -d ${D}/boot
    install -m 0755 ${WORKDIR}/vmlinuz-initrd-7439b0 ${D}/boot/initrd_run.bin
}

SRC_URI[md5sum] = "3b07468cf880a09081d6256867492103"
SRC_URI[sha256sum] = "89b486736457c14f75984cfe3c8bb236d4a1a51771085992fea7344876daf163"
