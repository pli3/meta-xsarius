require xsarius-initrd.inc

do_install() {
    install -d ${D}/boot
    install -m 0755 ${WORKDIR}/vmlinuz-initrd-7439b0 ${D}/boot/initrd_run.bin
}

SRC_URI[md5sum] = "c2a70c90aec8623f148a451b9a1f9eec"
SRC_URI[sha256sum] = "39cea89f80c732961bfd090b190b7f3d4f54b6379807cabd34ca6a0a89d6890f"
