# We know about rtc
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

include conf/machine/include/autoload-filesystems.inc

MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	xsarius-dvb-modules-${CHIPSET} \
	xsarius-kernel-params \
	xsarius-shutdown \
	ntfs-3g \
"

KERNEL_MODULE_AUTOLOAD += "xfs"

MACHINE_EXTRA_RRECOMMENDS = " \
	${@bb.utils.contains("DISTRO_VERSION", "4", "xsarius-gst-plugin-dvbmediasink", " \
	gstreamer1.0-plugin-xsarius-dvbmediasink gstreamer1.0-libav", d)} \
	splash-bootlogo \
	enigma2-plugin-extensions-hbbtv-xsarius \
	xsarius-opera-browser \
	xsarius-opera-dumpait \
	bcm \
"

EXTRA_IMAGEDEPENDS = " \
	splash-bootlogo \
"

TARGET_ARCH = "mipsel"
DEFAULTTUNE = "mips32el"

DVBMEDIASINK_CONFIG = "--with-wmv --with-pcm --with-dts --with-h265"

PREFERRED_PROVIDER_virtual/kernel = "linux-xsarius"
PREFERRED_VERSION_linux-xsarius = "4.2.1"

IMAGE_FSTYPES ?= "ubi"

UBI_VOLNAME = "rootfs"
MKUBIFS_ARGS = "-m 2048 -e 126976 -c 4096 -F"
UBINIZE_ARGS = "-m 2048 -p 128KiB"

EXTRA_IMAGECMD_COMPAT = " --eraseblock=0x20000 -n -l --pagesize=0x800 "

IMAGE_CMD_ubi_prepend = " \
        mkfs.jffs2 --root=${IMAGE_ROOTFS}/boot --faketime \
        --disable-compressor=lzo --compression-mode=size \
        --output=${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.boot.jffs2 \
        ${EXTRA_IMAGECMD_COMPAT}; rm -rf ${IMAGE_ROOTFS}/boot/; \
"

IMAGE_CMD_ubi_append = " \
	mkdir -p ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/; \
	cp -f ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubi ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/oe_rootfs.bin; \
	cp -f ${DEPLOY_DIR_IMAGE}/${MACHINE}_splash.bmp ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/splash.bmp; \
	cp -f ${DEPLOY_DIR_IMAGE}/${MACHINE}_splash1.bmp ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/splash1.bmp; \
	cp -f ${DEPLOY_DIR_IMAGE}/${MACHINE}_splash2.bmp ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/splash2.bmp; \
	cp -f ${DEPLOY_DIR_IMAGE}/${MACHINE}_splash3.bmp ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/splash3.bmp; \
	gzip -9c ${DEPLOY_DIR_IMAGE}/vmlinux-${MACHINE}.bin > ${DEPLOY_DIR_IMAGE}/${IMAGEDIR}/oe_kernel.bin; \
	cd ${DEPLOY_DIR_IMAGE}; \
	zip ${IMAGE_NAME}_usb.zip ${IMAGEDIR}/*; \
	rm -rf update; \
"

MACHINE_FEATURES += "dvb-c alsa usbhost wlan kernelwifi 3dtv switchoff osdposition hdmicec dvbusb"

require conf/machine/include/tune-mips32.inc
require conf/machine/include/xsarius-wifi.inc
require conf/machine/include/xsarius-dvb-usb.inc
