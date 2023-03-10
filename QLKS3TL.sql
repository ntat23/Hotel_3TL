USE [master]
GO
/****** Object:  Database [QLKS_3TL_Final]    Script Date: 8/18/2022 9:39:25 PM ******/
CREATE DATABASE [QLKS_3TL_Final]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QLKS_3TL_Final', FILENAME = N'E:\LT Java1\New folder (2)\MSSQL12.MSSQLSERVER2\MSSQL\DATA\QLKS_3TL_Final.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QLKS_3TL_Final_log', FILENAME = N'E:\LT Java1\New folder (2)\MSSQL12.MSSQLSERVER2\MSSQL\DATA\QLKS_3TL_Final_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [QLKS_3TL_Final] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QLKS_3TL_Final].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QLKS_3TL_Final] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET ARITHABORT OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QLKS_3TL_Final] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QLKS_3TL_Final] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QLKS_3TL_Final] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QLKS_3TL_Final] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET RECOVERY FULL 
GO
ALTER DATABASE [QLKS_3TL_Final] SET  MULTI_USER 
GO
ALTER DATABASE [QLKS_3TL_Final] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QLKS_3TL_Final] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QLKS_3TL_Final] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QLKS_3TL_Final] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QLKS_3TL_Final] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'QLKS_3TL_Final', N'ON'
GO
USE [QLKS_3TL_Final]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 8/18/2022 9:39:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[HoaDon](
	[MaHD] [varchar](5) NOT NULL,
	[MaNV] [varchar](7) NOT NULL,
	[MaPDK] [int] IDENTITY(1,1) NOT NULL,
	[CMND] [varchar](12) NOT NULL,
	[SoPhong] [varchar](3) NOT NULL,
	[GhiChu] [nvarchar](255) NULL,
	[NgayTao] [date] NULL,
	[TongTien] [money] NULL,
	[SoNgay] [int] NULL,
 CONSTRAINT [PK__HoaDon__2725A6E0BA0DD407] PRIMARY KEY CLUSTERED 
(
	[MaHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 8/18/2022 9:39:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[KhachHang](
	[CMND] [varchar](12) NOT NULL,
	[TenKH] [nvarchar](50) NOT NULL,
	[SDT] [varchar](11) NULL,
	[NamSinh] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[CMND] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[LoaiPhong]    Script Date: 8/18/2022 9:39:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LoaiPhong](
	[MaLoaiPhong] [varchar](10) NOT NULL,
	[TenLoaiPhong] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaLoaiPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 8/18/2022 9:39:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [varchar](7) NOT NULL,
	[Ten] [nvarchar](50) NOT NULL,
	[Diachi] [nvarchar](255) NULL,
	[NamSinh] [date] NULL,
	[Gioitinh] [bit] NULL,
	[SDT] [varchar](11) NOT NULL,
	[ChucVu] [bit] NOT NULL,
	[MatKhau] [varchar](10) NOT NULL,
 CONSTRAINT [PK__NhanVien__2725D70A5D4E4487] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Phieu_DK]    Script Date: 8/18/2022 9:39:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Phieu_DK](
	[MaPDK] [int] IDENTITY(1,1) NOT NULL,
	[SoPhong] [varchar](3) NOT NULL,
	[SoNgay] [int] NOT NULL,
	[CMND] [varchar](12) NOT NULL,
	[Ngay_Checkin] [date] NOT NULL,
	[Ngay_Checkout] [date] NULL,
	[GhiChu] [nvarchar](255) NULL,
	[MaNV] [varchar](7) NULL,
	[GioVao] [varchar](7) NULL,
	[GioRa] [varchar](7) NULL,
	[TinhTrangCho] [bit] NULL,
	[TinhTrangHoaDon] [bit] NULL CONSTRAINT [DF_Phieu_DK_TinhTrangHoaDon]  DEFAULT ((0)),
 CONSTRAINT [PK__Phieu_DK__3AE048C2E9362D89] PRIMARY KEY CLUSTERED 
(
	[MaPDK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Phong]    Script Date: 8/18/2022 9:39:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Phong](
	[SoPhong] [varchar](3) NOT NULL,
	[GiaPhong_ngay] [money] NULL,
	[GiaPhong_gioi] [money] NULL,
	[CMND] [varchar](12) NULL,
	[MaLoaiPhong] [varchar](10) NULL,
	[TinhTrang] [varchar](10) NULL,
 CONSTRAINT [PK__Phong__7C736CA00A4867BE] PRIMARY KEY CLUSTERED 
(
	[SoPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [HD_KH] FOREIGN KEY([CMND])
REFERENCES [dbo].[KhachHang] ([CMND])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [HD_KH]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [HD_NV] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [HD_NV]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [HD_Phog] FOREIGN KEY([SoPhong])
REFERENCES [dbo].[Phong] ([SoPhong])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [HD_Phog]
GO
ALTER TABLE [dbo].[Phieu_DK]  WITH CHECK ADD  CONSTRAINT [FK_Phieu_DK_NV] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[Phieu_DK] CHECK CONSTRAINT [FK_Phieu_DK_NV]
GO
ALTER TABLE [dbo].[Phieu_DK]  WITH CHECK ADD  CONSTRAINT [PDK_KH] FOREIGN KEY([CMND])
REFERENCES [dbo].[KhachHang] ([CMND])
GO
ALTER TABLE [dbo].[Phieu_DK] CHECK CONSTRAINT [PDK_KH]
GO
ALTER TABLE [dbo].[Phieu_DK]  WITH CHECK ADD  CONSTRAINT [PDK_Phog] FOREIGN KEY([SoPhong])
REFERENCES [dbo].[Phong] ([SoPhong])
GO
ALTER TABLE [dbo].[Phieu_DK] CHECK CONSTRAINT [PDK_Phog]
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD  CONSTRAINT [PHog_KH] FOREIGN KEY([CMND])
REFERENCES [dbo].[KhachHang] ([CMND])
GO
ALTER TABLE [dbo].[Phong] CHECK CONSTRAINT [PHog_KH]
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD  CONSTRAINT [PHog_LP] FOREIGN KEY([MaLoaiPhong])
REFERENCES [dbo].[LoaiPhong] ([MaLoaiPhong])
GO
ALTER TABLE [dbo].[Phong] CHECK CONSTRAINT [PHog_LP]
GO
/****** Object:  StoredProcedure [dbo].[sp_danhsachphongcho]    Script Date: 8/18/2022 9:39:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_danhsachphongcho]
as begin 
select 
	pdk.MaPDK,	
	kh.TenKH,
	kh.SDT,
	pdk.CMND,
	p.SoPhong,
	lp.TenLoaiPhong,
	pdk.Ngay_Checkin,
	pdk.Ngay_checkout,
	pdk.GioVao,
	pdk.GioRa
	

	from Phieu_DK pdk
	join Phong p on pdk.SoPhong = p.SoPhong 
	join KhachHang kh on pdk.CMND = kh.CMND
	join LoaiPhong lp on p.MaLoaiPhong = lp.MaLoaiPhong
	where  pdk.TinhTrangCho=1
	group by  pdk.MaPDK,
	pdk.MaPDK,	
	kh.TenKH,
	kh.SDT,
	pdk.CMND,
	p.SoPhong,
	lp.TenLoaiPhong,
	pdk.Ngay_Checkin,
	pdk.Ngay_checkout,
	pdk.GioVao,
	pdk.GioRa

	end




GO
/****** Object:  StoredProcedure [dbo].[sp_FILLformdatPHong]    Script Date: 8/18/2022 9:39:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_FILLformdatPHong] (@MaPDK int)
	as begin
		select
		pdk.SoPhong,ph.MaLoaiPhong,
		TenKH,Ngay_Checkin,Ngay_Checkout,
		nv.Ten
			
			
		from Phieu_DK pdk
			join NhanVien nv on pdk.MaNV = nv.MaNV
			join KhachHang kh on pdk.CMND = kh.CMND
			join Phong ph on  pdk.SoPhong = ph.SoPhong
		
	end


GO
/****** Object:  StoredProcedure [dbo].[sp_pdk]    Script Date: 8/18/2022 9:39:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[sp_pdk] (@SoPhong varchar(3))
as begin 
select pdk.MaPDK,
	p.SoPhong,
	pdk.CMND,
	pdk.Ngay_Checkin,
	pdk.Ngay_checkout,
	pdk.GioVao,
	pdk.GioRa,
	p.TinhTrang,
	pdk.TinhTrangCho

	from Phieu_DK pdk
	join Phong p on pdk.SoPhong = p.SoPhong 
	where @SoPhong = pdk.SoPhong and pdk.TinhTrangHoaDon = 0
	group by  pdk.MaPDK,
	p.SoPhong,
	pdk.CMND,
	pdk.Ngay_Checkin,
	pdk.Ngay_checkout,
	pdk.GioVao,
	pdk.GioRa,
	p.TinhTrang,
	pdk.TinhTrangCho
	end




GO
/****** Object:  StoredProcedure [dbo].[sp_showkhachhangvaphongdadat]    Script Date: 8/18/2022 9:39:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_showkhachhangvaphongdadat] (@MaKH varchar(11))
as begin
		select
			kh.TenKH,
			hd.CMND,
			kh.SDT,
			ph.MaLoaiPhong,
			pdk.SoPhong,
			hd.TongTien,
			pdk.Ngay_Checkin,
			pdk.Ngay_Checkout,
			hd.SoNgay
			

			
			
			from  HoaDon hd
			join Phieu_DK pdk on  hd.MaPDK=pdk.MaPDK 
			join KhachHang kh on hd.CMND = kh.CMND
			join Phong ph on hd.SoPhong = ph.SoPhong
			
		where @MaKH = hd.CMND
		group by 
			kh.TenKH,
			hd.CMND,
			kh.SDT,
			ph.MaLoaiPhong,
			pdk.SoPhong,
			hd.TongTien,
			pdk.Ngay_Checkin,
			pdk.Ngay_Checkout,
			hd.SoNgay
		
	end


GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeDoanhThuNam]    Script Date: 8/18/2022 9:39:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_ThongKeDoanhThuNam] (@Year int)
	as begin
		select
			kh.TenKH,
			pdk.CMND,
			kh.SDT,
			ph.MaLoaiPhong,
			pdk.SoPhong,
			

			
			sum(TongTien) Tong
		from Phieu_DK pdk
			join HoaDon hd on pdk.MaPDK = hd.MaPDK
			join KhachHang kh on pdk.CMND = kh.CMND
			join Phong ph on pdk.SoPhong = ph.SoPhong
			
		where Year(Ngay_Checkin) = @Year
		group by Month(Ngay_Checkin),Year(Ngay_Checkin) ,kh.TenKH,
			pdk.CMND,
			kh.SDT,
			ph.MaLoaiPhong,
			pdk.SoPhong,
			hd.TongTien
	end


GO
/****** Object:  StoredProcedure [dbo].[sp_ThongKeDoanhThuThangNam]    Script Date: 8/18/2022 9:39:26 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[sp_ThongKeDoanhThuThangNam] (@Month int, @Year int)
	as begin
		select
			kh.TenKH,
			pdk.CMND,
			kh.SDT,
			ph.MaLoaiPhong,
			pdk.SoPhong,
			

			
			sum(TongTien) Tong
		from Phieu_DK pdk
			join HoaDon hd on pdk.MaPDK = hd.MaPDK
			join KhachHang kh on pdk.CMND = kh.CMND
			join Phong ph on pdk.SoPhong = ph.SoPhong
			
		where Month(Ngay_Checkin) = @Month and Year(Ngay_Checkin) = @Year
		group by Month(Ngay_Checkin),Year(Ngay_Checkin) ,kh.TenKH,
			pdk.CMND,
			kh.SDT,
			ph.MaLoaiPhong,
			pdk.SoPhong,
			hd.TongTien
	end


GO
USE [master]
GO
ALTER DATABASE [QLKS_3TL_Final] SET  READ_WRITE 
GO
