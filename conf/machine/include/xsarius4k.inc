# We know about rtc
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

MACHINE_ESSENTIAL_EXTRA_RDEPENDS = " \
	${@base_contains("GST_VERSION", "1.0", "gstreamer1.0-libav", "gst-ffmpeg", d)} \
        xsarius-initrd-${MACHINE} \
        xsarius-shutdown \
        xsarius-dvb-modules-bcm7252s \
"

KERNEL_MODULE_AUTOLOAD += "xfs"

MACHINE_EXTRA_RRECOMMENDS = " \
	xsarius-gstreamer1.0-plugin-dvbmediasink \
	ntfs-3g \
"

EXTRA_IMAGEDEPENDS += "\
                   splash-bootlogo \
"

TARGET_ARCH = "arm"
DEFAULTTUNE = "armv7ahf-neon"

DVBMEDIASINK_CONFIG = "--with-dags --with-pcm --with-eac3 --with-amr --with-wmv --with-vb6 --with-vb8 --with-spark --with-h265"

PREFERRED_VERSION_linux-xsarius = "3.14.28"

PREFERRED_PROVIDER_virtual/kernel = "linux-xsarius"

IMAGE_FSTYPES ?= "tar.bz2"

IMAGEDIR ?= "${MACHINE}"
IMGDEPLOYDIR ?= "${DEPLOY_DIR_IMAGE}"

IMAGE_CMD_tar_prepend = " \
	mkdir -p ${IMGDEPLOYDIR}/update/${UPDATE_DIR}/; \
        cp -f ${IMGDEPLOYDIR}/${MACHINE}_splash.bmp ${IMGDEPLOYDIR}/update/${UPDATE_DIR}/splash.bmp; \
        cp ${IMAGE_ROOTFS}/tmp/zImage ${IMGDEPLOYDIR}/update/${MACHINE}/oe_kernel.bin; \
        cp ${IMAGE_ROOTFS}/boot/initrd_run.bin ${IMGDEPLOYDIR}/update/${UPDATE_DIR}/initrd_run.bin; \
        rm -rf ${IMAGE_ROOTFS}/tmp/zImage; \
        rm -rf ${IMAGE_ROOTFS}/boot/initrd_run.bin; \
        tar -cvf ${IMGDEPLOYDIR}/rootfs.tar -C ${IMAGE_ROOTFS} .; \
        mv ${IMGDEPLOYDIR}/rootfs.tar ${IMGDEPLOYDIR}/update/${UPDATE_DIR}/rootfs.tar; \
        bzip2 ${IMGDEPLOYDIR}/update/${MACHINE}/rootfs.tar; \
        cd ${IMGDEPLOYDIR}; \
        zip ${IMAGE_NAME}_usb.zip update/${UPDATE_DIR}/*; \
        rm -rf update; \
"

MACHINE_FEATURES += "alsa usbhost wlan kernelwifi extrakernelwifi 3dtv switchoff osdposition hdmicec"

require conf/machine/include/arm/arch-armv7a.inc
require conf/machine/include/xsarius-wifi.inc
require conf/machine/include/xsarius-dvb-usb.inc
