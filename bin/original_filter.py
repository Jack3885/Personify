# Feel free to modify and use this filter however you wish. If you do,
# please give credit to SethBling.
# http://youtube.com/SethBling

from pymclevel import TAG_List
from pymclevel import TAG_Byte
from pymclevel import TAG_Int
from pymclevel import TAG_Compound
from pymclevel import TAG_Short
from pymclevel import TAG_Double
from pymclevel import TAG_String
from pymclevel import TAG_Int_Array
from httplib import HTTPConnection
import png

displayName = "Player Statue"

inputs = (
	("Character Name", "string"),
)

materials = [
	(1,   0,  125, 125, 125),
	(3,   0,  134,  96,  67),
	(5,   0,  156, 127,  78),
	(5,   1,  103,  77,  46),
	(5,   2,  195, 179, 123),
	(5,   3,  154, 110,  77),
	(22,  0,   29,  71, 165),
	(24,  0,  229, 221, 168),
	(25,  0,  100,  67,  50),
	(35,  0,  221, 221, 221),
	(35,  1,  219, 125,  62),
	(35,  2,  179,  80, 188),
	(35,  3,  107, 138, 201),
	(35,  4,  177, 166,  39),
	(35,  5,   65, 174,  56),
	(35,  6,  208, 132, 153),
	(35,  7,   64,  64,  64),
	(35,  8,  154, 161, 161),
	(35,  9,   46, 110, 137),
	(35, 10,  126,  61, 181),
	(35, 11,   46,  56, 141),
	(35, 12,   79,  50,  31),
	(35, 13,   53,  70,  27),
	(35, 14,  150,  52,  48),
	(35, 15,   25,  22,  22),
	(41,  0,  249, 236,  78),
	(42,  0,  219, 219, 219),
	(45,  0,  146,  99,  86),
	(49,  0,   20,  18,  29),
	(57,  0,   97, 219, 213),
	(80,  0,  239, 251, 251),
	(82,  0,  158, 164, 176),
	(87,  0,  111,  54,  52),
	(88,  0,   84,  64,  51),
	(98,  0,  122, 122, 122),
	(103, 0,  141, 145,  36),
	(112, 0,   44,  22,  46),
	(121, 0,  221, 223, 165),
	(133, 0,   81, 217, 117),
	#(152, 0,  171,  27,   9), # 1.5
	#(155, 0,  236, 233, 226), # 1.5
]

def getPixel(pixels, x, y):
	idx = x*4
	return (pixels[y][idx], pixels[y][idx+1], pixels[y][idx+2], pixels[y][idx+3])
	
def transparent((r, g, b, a)):
	return a < 128
	
def closestMaterial((r, g, b, a)):
	closest = 255*255*3
	best = (35, 0)
	for (mat, dat, mr, mg, mb) in materials:
		(dr, dg, db) = (r-mr, g-mg, b-mb)
		dist = dr*dr+dg*dg+db*db
		if dist < closest:
			closest = dist
			best = (mat, dat)
	
	return best

XDIM = 1
YDIM = 2
ZDIM = 3
	
regions = [
	(8, 16, 0, 8, XDIM, ZDIM, 4, 31, 1),	# Head Top
	(16, 24, 0, 8, XDIM, -ZDIM, 4, 24, 1),	# Head Bottom
	(24, 32, 8, 16, XDIM, YDIM, 4, 24, 8),	# Head Back
	(0, 8, 8, 16, -ZDIM, YDIM, 4, 24, 1),	# Head Left
	(16, 24, 8, 16, ZDIM, YDIM, 11, 24, 1),	# Head Right
	(8, 16, 8, 16, XDIM, YDIM, 4, 24, 1),	# Head Front
	(40, 48, 0, 8, XDIM, ZDIM, 4, 32, 1),	# Hat Top
	(56, 64, 8, 16, XDIM, YDIM, 4, 24, 9),	# Hat Back
	(32, 40, 8, 16, -ZDIM, YDIM, 3, 24, 1),	# Hat Left
	(48, 56, 8, 16, ZDIM, YDIM, 12, 24, 1),	# Hat Right
	(40, 48, 8, 16, XDIM, YDIM, 4, 24, 0),	# Hat Front
	(20, 28, 16, 20, XDIM, ZDIM, 4, 23, 3), # Body Top
	(28, 36, 16, 20, XDIM, -ZDIM, 4, 12, 3),# Body Bottom
	(32, 40, 20, 32, XDIM, YDIM, 4, 12, 6), # Body Back
	(16, 20, 20, 32, -ZDIM, YDIM, 4, 12, 3),# Body Left
	(28, 32, 20, 32, ZDIM, YDIM, 11, 12, 3),# Body Right
	(20, 28, 20, 32, XDIM, YDIM, 4, 12, 3),	# Body Front
	(4, 8, 16, 20, XDIM, ZDIM, 4, 11, 3),	# Left Leg Top
	(4, 8, 16, 20, XDIM, ZDIM, 8, 11, 3),	# Right Leg Top
	(8, 12, 16, 20, XDIM, -ZDIM, 4, 0, 3),	# Left Leg Bottom
	(8, 12, 16, 20, XDIM, -ZDIM, 8, 0, 3),	# Right Leg Bottom
	(12, 16, 20, 32, XDIM, YDIM, 4, 0, 6),	# Left Leg Back
	(12, 16, 20, 32, XDIM, YDIM, 8, 0, 6),	# Right Leg Back
	(0, 4, 20, 32, -ZDIM, YDIM, 4, 0, 3),	# Legs Left
	(8, 12, 20, 32, ZDIM, YDIM, 11, 0, 3),	# Legs Right
	(4, 8, 20, 32, XDIM, YDIM, 4, 0, 3),	# Left Leg Front
	(4, 8, 20, 32, XDIM, YDIM, 8, 0, 3),	# Right Leg Front
	(44, 48, 16, 20, XDIM, ZDIM, 0, 23, 3),	# Left Arm Top
	(44, 48, 16, 20, XDIM, ZDIM, 12, 23, 3),	# Right Arm Top
	(48, 52, 16, 20, XDIM, -ZDIM, 0, 12, 3),	# Left Arm Bottom
	(48, 52, 16, 20, XDIM, -ZDIM, 12, 12, 3),# Right Arm Bottom
	(52, 56, 20, 32, XDIM, YDIM, 0, 12, 6),	# Left Arm Back
	(52, 56, 20, 32, XDIM, YDIM, 12, 12, 6),	# Right Arm Back
	(40, 44, 20, 32, -ZDIM, YDIM, 0, 12, 3),	# Arms Left
	(48, 52, 20, 32, ZDIM, YDIM, 15, 12, 3),	# Arms Right
	(44, 48, 20, 32, XDIM, YDIM, 0, 12, 3),	# Left Arm Front
	(44, 48, 20, 32, XDIM, YDIM, 12, 12, 3),	# Right Arm Front
]

def perform(level, box, options):
	host = "s3.amazonaws.com"
	conn = HTTPConnection(host, timeout=10000)
	path = "/MinecraftSkins/" + options["Character Name"] + ".png"
	print "Retrieving " + path + " from " + host
	conn.request("GET", path)
	response = conn.getresponse()
	print response.status, response.reason
		
	data = response.read()
	conn.close()
	
	reader = png.Reader(bytes=data)
	(width, height, pixels, metadata) = reader.asRGBA8()
	pixels = list(pixels)
	
	for (xl, xh, yl, yh, dim1, dim2, sx, sy, sz) in regions:
		print (xl, xh, yl, yh, dim1, dim2, sx, sy, sz)
		lx = sx
		ly = sy
		lz = sz
		if dim1 == -ZDIM:
			lz = sz + xh-xl - 1
		if dim2 == -ZDIM:
			lz = sz + yh-yl - 1
		
		for px in range(xl, xh):
			for py in reversed(range(yl, yh)):
				color = getPixel(pixels, px, py)
				if not transparent(color):
					(mat, dat) = closestMaterial(color)
					level.setBlockAt(box.minx + 15-lx, box.miny + ly, box.minz + lz, mat)
					level.setBlockDataAt(box.minx + 15-lx, box.miny + ly, box.minz + lz, dat)
				
				if dim2 == YDIM:
					ly = ly + 1
				if dim2 == ZDIM:
					lz = lz + 1
				if dim2 == -ZDIM:
					lz = lz - 1
			
			if dim2 == YDIM:
				ly = sy
			if dim2 == ZDIM:
				lz = sz
			if dim2 == -ZDIM:
				lz = sz + yh-yl - 1
			
			if dim1 == XDIM:
				lx = lx + 1
			if dim1 == ZDIM:
				lz = lz + 1
			if dim1 == -ZDIM:
				lz = lz - 1
					