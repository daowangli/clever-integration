USE [master]
GO
CREATE DATABASE [clever-integration]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'clever-integration', FILENAME = N'J:\DB\clever-integration.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'clever-integration_log', FILENAME = N'J:\DB\clever-integration_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [clever-integration] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [clever-integration].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [clever-integration] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [clever-integration] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [clever-integration] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [clever-integration] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [clever-integration] SET ARITHABORT OFF 
GO
ALTER DATABASE [clever-integration] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [clever-integration] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [clever-integration] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [clever-integration] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [clever-integration] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [clever-integration] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [clever-integration] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [clever-integration] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [clever-integration] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [clever-integration] SET  DISABLE_BROKER 
GO
ALTER DATABASE [clever-integration] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [clever-integration] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [clever-integration] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [clever-integration] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [clever-integration] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [clever-integration] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [clever-integration] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [clever-integration] SET RECOVERY FULL 
GO
ALTER DATABASE [clever-integration] SET  MULTI_USER 
GO
ALTER DATABASE [clever-integration] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [clever-integration] SET DB_CHAINING OFF 
GO
ALTER DATABASE [clever-integration] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [clever-integration] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [clever-integration] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'clever-integration', N'ON'
GO
USE [clever-integration]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Queue](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[tableName] [nvarchar](max) NOT NULL,
	[logicalKeyName] [nvarchar](max) NOT NULL,
	[logicalKeyValue] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Queue] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QueueLog](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[tableName] [nvarchar](max) NOT NULL,
	[logicalKeyName] [nvarchar](max) NOT NULL,
	[logicalKeyValue] [nvarchar](max) NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_QueueLog] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
USE [master]
GO
ALTER DATABASE [clever-integration] SET  READ_WRITE 
GO
